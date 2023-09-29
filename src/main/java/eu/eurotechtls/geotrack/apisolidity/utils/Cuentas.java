package eu.eurotechtls.geotrack.apisolidity.utils;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import eu.eurotechtls.geotrack.apisolidity.controler.Huella_sol_Huella;

public class Cuentas {

    final Logger LOG = Logger.getLogger("eu.eurotechtls.utils.Cuentas");

    private void manejoLogs() {
        LOG.setLevel(Level.FINE);
        try {
            FileHandler fh = new FileHandler("Cuentaslogs.log");
            LOG.addHandler(fh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Credentials crearCuenta() {
        manejoLogs();
        Credentials credentials = Credentials.create("CAD1234EE");
        ECKeyPair ecKeyPair = credentials.getEcKeyPair();
        LOG.log(Level.INFO,"Clave pública: " + ecKeyPair.getPublicKey().toString());
        return credentials;
    }

    /*
     * Cargar la cuenta del transporte
     * @param web3 conexión con la red
     * @return credentials credenciales de la cuenta
     * @throws IOException excepción cargando la cuenta del transporte => devuelve null
     */
    public Credentials usarCuenta(String pvKey){
        manejoLogs();

        try {
            //account = "0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2";
           // web3.ethAccounts().send().setJsonrpc(account);
            Credentials credentials = Credentials.create(pvKey);
            ECKeyPair keyPair = credentials.getEcKeyPair();
            BigInteger privateKey = keyPair.getPrivateKey();
            BigInteger publicKey = keyPair.getPublicKey();
            LOG.log(Level.INFO,"Clave pública: " + publicKey.toString());
            LOG.log(Level.INFO,"Clave privada: " + privateKey.toString());
            return credentials;
        } catch (Exception e) {
            LOG.log(Level.SEVERE,"Excepción cargando la cuenta: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    //Desplegar el contrato
    public Huella_sol_Huella deployContract(Web3j web3, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String owner, BigInteger transportId) {
        manejoLogs();
        Huella_sol_Huella document = null;
        try {
            document = Huella_sol_Huella.deploy(web3, transactionManager, contractGasProvider, owner, transportId).send();
        } catch (Exception e) {
            LOG.log(Level.SEVERE,"Excepcines de despliegue :" + e.getMessage());
            e.printStackTrace();
        }
        return document;
    }

    //Cargar el contrato
    public Huella_sol_Huella loadContractHuella(Web3j web3, Credentials credentials, ContractGasProvider contractGasProvider, String contractAddress) {
        manejoLogs();
        Huella_sol_Huella document = Huella_sol_Huella.load(contractAddress, web3, credentials, contractGasProvider);
        return document;
    }

     //Obtener las huellas del contrato
    public List getHuellas(Huella_sol_Huella huella) {
        manejoLogs();
        List receipt = null;
        try {
            receipt =   huella.getHuella().send();
        } catch (Exception e) {
            LOG.log(Level.SEVERE,"Excepciones de getHuellas ");
            LOG.log(Level.SEVERE,"Error al obtener huellas: " + e.getMessage());
            e.printStackTrace();
        }
        return receipt;
    }


    //Añadir huella al contrato
    public TransactionReceipt addHuella(Huella_sol_Huella huella, String datos) {
        manejoLogs();
        TransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = huella.setHuella(datos).send();
        } catch (Exception e) {
            // Excepciones de llamada
            LOG.log(Level.SEVERE,"Error al añadir huella: " + e.getMessage());
            e.printStackTrace();
        }
        return transactionReceipt;
    }

    //Formatear huela para añadir al contrato sustituyendo las comillas por $
    public String formatHuella(String huella) {
        manejoLogs();
        String huellaFormateada = huella.replace("\"", "$");
        return huellaFormateada;
    }

    //Formatear huela leida del contrato sustituyendo $ por las comillas
    public String formatHuellaRead(String huella) {
        manejoLogs();
        String huellaFormateada = huella.replace("$", "\"");
        return huellaFormateada;
    }

}
