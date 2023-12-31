package eu.eurotechtls.geotrack.apisolidity.controler;

import eu.eurotechtls.geotrack.apisolidity.utils.Cuentas;

import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.PrivateTransactionManager;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;

@RestController
@RequestMapping("")
public class apisolidityControler {
	
	final Logger LOG = Logger.getLogger("eu.eurotechtls.geotrack.apisolidity.Controler");
	//final String URL = "http://141.94.208.126:8545";
	//final String URL = "http://127.0.0.1:7545";
	final String URL = "http://37.59.27.182:8545";
	
	//Proveedor de gas por defecto
    ContractGasProvider contractGasProvider = new ContractGasProvider() {
        @Override
        public BigInteger getGasPrice(String contractFunction) {
            return new BigInteger("0"); //20000000000
        }

        @Override
        public BigInteger getGasPrice() {
            return new BigInteger("0"); //20000000000
        }

        @Override
        public BigInteger getGasLimit(String contractFuncion) {
            return new BigInteger("6721975");
        }

        @Override
        public BigInteger getGasLimit() {
            return new BigInteger("6721975");
        }
    };
	
	private Web3j obtenWeb3(String url) {
		// Conexión con la red besu usando el nodo de la empresa en la url que se recibe como parámetro:
        Web3j web3 = Web3j.build(new HttpService(url));
        LOG.log(Level.INFO,url + " " + " Protocolo: " + web3.ethProtocolVersion().getId());
        try {
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            LOG.log(Level.INFO,"Client version: " + clientVersion);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return web3;
	}
	
	private void manejoLogs() {
        LOG.setLevel(Level.FINE);
        try {
            FileHandler fh = new FileHandler("Controlerlogs.log");
            LOG.addHandler(fh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/********************************************************************/	
	/* Leer huella => 											*/
	/* 1. Usar como propietario la PvKey que se recibe por parámetro 	*/
	/* 2. Conectar con la red Besu										*/
	/* 3. Desplegar el contrato Huella_sol_Huella						*/
	/* 4. Devolver la dirección del contrato							*/
	/********************************************************************/
		
	@GetMapping("/huellas")
	public ResponseEntity<List<String>> findHuellas() {
		System.out.println("Buscando huellas: ");
		manejoLogs();
		
		LOG.log(Level.INFO,"Buscando huellas: " );
		// Conexión con la red besu usando el nodo de la empresa:
        //String url = "http://141.94.208.126:8545";
        //String url = "http://127.0.0.1:7545";
        Web3j web3 = Web3j.build(new HttpService(URL));
        LOG.log(Level.INFO,URL + " " + " Protocolo: " + web3.ethProtocolVersion().getId());
        try {
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            LOG.log(Level.INFO,"Client version: " + clientVersion);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
        }
        
        // Credenciales de la cuenta de la empresa
        Cuentas cuenta = new Cuentas();
        Credentials credentials = cuenta.usarCuenta("0x5f88ac17c4bc1375899f35ee6730219ffcb1648e0d5cbe649f1ef39365ee2914");
        System.out.println("Cuenta: " + credentials.getAddress());
        
         
        
		//Obtener el contrato
        LOG.log(Level.INFO,"Dirección de la cuenta : " + credentials.getAddress());
        Huella_sol_Huella document = new Huella_sol_Huella("0xbcdc6b18be15ef4e8a7184a1b4727ce9ef3bc310", web3, credentials, contractGasProvider);
        LOG.log(Level.INFO,"Dirección del contrato : " + document.getContractAddress());
       try {
    	   LOG.log(Level.INFO,"Propietario del contrato : " + document.owner().send());
      } catch (Exception e) {
           // Error en la gestión de la llamda
    	  LOG.log(Level.INFO,"Error al obtener el propietario: " + e.getMessage());
          return new ResponseEntity<List<String>>(HttpStatus.UNAUTHORIZED);
          //e.printStackTrace();
      }
       
     //Llamada a la lista de huellas
       System.out.println("Llamando a las huellas: " );
      try {
    	  LOG.log(Level.INFO,"01: " + credentials.getAddress());
          @SuppressWarnings("unchecked")
          List<String> huellaList = document.getHuella().send();
          huellaList.forEach((huella) -> {
        	  LOG.log(Level.INFO,"Huella: " + "(" + huellaList.indexOf(huella) + ") " + huella);
          });
          return new ResponseEntity<List<String>>(huellaList, HttpStatus.OK);

      } catch (Exception e) {
          // Error en el tipo de datos recibido
    	  LOG.log(Level.INFO,"Error en la petición: " + e.getMessage());
          e.printStackTrace();
          return new ResponseEntity<List<String>>(HttpStatus.NOT_ACCEPTABLE);
      }						
	}

	/********************************************************************/	
	/* Desplegar contrato => 											*/
	/* 1. Usar como propietario la PvKey que se recibe por parámetro 	*/
	/* 2. Conectar con la red Besu										*/
	/* 3. Desplegar el contrato Huella_sol_Huella						*/
	/* 4. Devolver la dirección del contrato							*/
	/********************************************************************/

	@PostMapping("/contract")
	public String addcontract(@RequestBody String owner) {
		String dirContrato = null;
		
		System.out.println("Owner : " + owner);
		Cuentas cuenta = new Cuentas();
		//Credentials credentials = cuenta.usarCuenta("0x5f88ac17c4bc1375899f35ee6730219ffcb1648e0d5cbe649f1ef39365ee2914");
		Credentials credentials = cuenta.usarCuenta(owner);
		
		Web3j web3 = obtenWeb3(URL);
		long chainId = 2022;
        
		//TransactionManager transactionManager = new RawTransactionManager(web3, credentials);
		TransactionManager transactionManager = new RawTransactionManager(web3,credentials, 50, 600);
		
		//Despliegue del contrato
        System.out.println("Despliegue del contrato : " + credentials.getAddress());
        //Huella_sol_Huella document = cuenta.deployContract(web3, transactionManager, contractGasProvider, credentials.getAddress(), new BigInteger("1"));
        //Huella_sol_Huella document = cuenta.deployContract(web3, transactionManager, contractGasProvider, owner, new BigInteger("1"));
        Huella_sol_Huella document;
		try {
			document = Huella_sol_Huella.deploy(web3, transactionManager, contractGasProvider, owner, new BigInteger("1")).send();
	        dirContrato = document.getContractAddress();
	        System.out.println("Documento : " + document.getContractAddress());
	        System.out.println("Documento : " + document.owner()); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dirContrato;
		//0xf6eb3116269103bb8bec3d130a471b567b21ff86
		//0xbcdc6b18be15ef4e8a7184a1b4727ce9ef3bc310
	}
	
	/************************************************************************/	
	/* Añadir una huella nueva => 											*/
	/* 1. Usar como propietario la PvKey que se recibe por parámetro 		*/
	/* 2. Usar el contrato que se recibe por parámetro						*/
	/* 3. Añadir la huella													*/
	/************************************************************************/	

	@PostMapping("/huella")
	public String addhuella(@RequestBody String owner) {
		System.out.println("Añadir huella: ");
		Web3j web3 = obtenWeb3(URL);
		Cuentas cuenta = new Cuentas();
		Credentials credentials = cuenta.usarCuenta(owner);
		Huella_sol_Huella document = new Huella_sol_Huella("0xbcdc6b18be15ef4e8a7184a1b4727ce9ef3bc310", web3, credentials, contractGasProvider); 		
		TransactionReceipt transactionReceipt = cuenta.addHuella(document,
				"$idhuella$: 98,$idtransporte$: 1,$idtramo$: 2,$longitud$: -3.577882,$latitud$: 40.453835,$altitud$: 600,$humedad$: 0.0,$temperatura$: 26.0,$movimientox$: 35.0,$movimientoy$: 29.0,$movimientoz$: 17.0,     $velocidad$: 83.0,$momento$: $2023-07-13 10:00:12$,$iddispositivo$: $1$");
		System.out.println("TransactionReceipt: " + transactionReceipt.toString());
		return transactionReceipt.toString();
	}

} 
