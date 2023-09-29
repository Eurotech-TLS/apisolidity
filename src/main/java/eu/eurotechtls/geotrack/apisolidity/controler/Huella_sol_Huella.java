package eu.eurotechtls.geotrack.apisolidity.controler;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class Huella_sol_Huella extends Contract {
    public static final String BINARY = "608060405260006001553480156200001657600080fd5b5060405162000cd938038062000cd983398181016040528101906200003c919062000130565b816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600181905550505062000177565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000620000bd8262000090565b9050919050565b620000cf81620000b0565b8114620000db57600080fd5b50565b600081519050620000ef81620000c4565b92915050565b6000819050919050565b6200010a81620000f5565b81146200011657600080fd5b50565b6000815190506200012a81620000ff565b92915050565b600080604083850312156200014a57620001496200008b565b5b60006200015a85828601620000de565b92505060206200016d8582860162000119565b9150509250929050565b610b5280620001876000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80632826c3301461005c578063578f408d1461007a5780638da5cb5b1461009857806399fbab88146100b6578063a4ffb850146100e6575b600080fd5b610064610102565b6040516100719190610390565b60405180910390f35b610082610108565b60405161008f91906104fd565b60405180910390f35b6100a06101e1565b6040516100ad9190610560565b60405180910390f35b6100d060048036038101906100cb91906105bb565b610205565b6040516100dd9190610632565b60405180910390f35b61010060048036038101906100fb9190610789565b6102b1565b005b60015481565b60606002805480602002602001604051908101604052809291908181526020016000905b828210156101d857838290600052602060002001805461014b90610801565b80601f016020809104026020016040519081016040528092919081815260200182805461017790610801565b80156101c45780601f10610199576101008083540402835291602001916101c4565b820191906000526020600020905b8154815290600101906020018083116101a757829003601f168201915b50505050508152602001906001019061012c565b50505050905090565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6002818154811061021557600080fd5b90600052602060002001600091509050805461023090610801565b80601f016020809104026020016040519081016040528092919081815260200182805461025c90610801565b80156102a95780601f1061027e576101008083540402835291602001916102a9565b820191906000526020600020905b81548152906001019060200180831161028c57829003601f168201915b505050505081565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461033f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103369061087e565b60405180910390fd5b6002819080600181540180825580915050600190039060005260206000200160009091909190915090816103739190610a4a565b5050565b6000819050919050565b61038a81610377565b82525050565b60006020820190506103a56000830184610381565b92915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b600081519050919050565b600082825260208201905092915050565b60005b838110156104115780820151818401526020810190506103f6565b60008484015250505050565b6000601f19601f8301169050919050565b6000610439826103d7565b61044381856103e2565b93506104538185602086016103f3565b61045c8161041d565b840191505092915050565b6000610473838361042e565b905092915050565b6000602082019050919050565b6000610493826103ab565b61049d81856103b6565b9350836020820285016104af856103c7565b8060005b858110156104eb57848403895281516104cc8582610467565b94506104d78361047b565b925060208a019950506001810190506104b3565b50829750879550505050505092915050565b600060208201905081810360008301526105178184610488565b905092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061054a8261051f565b9050919050565b61055a8161053f565b82525050565b60006020820190506105756000830184610551565b92915050565b6000604051905090565b600080fd5b600080fd5b61059881610377565b81146105a357600080fd5b50565b6000813590506105b58161058f565b92915050565b6000602082840312156105d1576105d0610585565b5b60006105df848285016105a6565b91505092915050565b600082825260208201905092915050565b6000610604826103d7565b61060e81856105e8565b935061061e8185602086016103f3565b6106278161041d565b840191505092915050565b6000602082019050818103600083015261064c81846105f9565b905092915050565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6106968261041d565b810181811067ffffffffffffffff821117156106b5576106b461065e565b5b80604052505050565b60006106c861057b565b90506106d4828261068d565b919050565b600067ffffffffffffffff8211156106f4576106f361065e565b5b6106fd8261041d565b9050602081019050919050565b82818337600083830152505050565b600061072c610727846106d9565b6106be565b90508281526020810184848401111561074857610747610659565b5b61075384828561070a565b509392505050565b600082601f8301126107705761076f610654565b5b8135610780848260208601610719565b91505092915050565b60006020828403121561079f5761079e610585565b5b600082013567ffffffffffffffff8111156107bd576107bc61058a565b5b6107c98482850161075b565b91505092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061081957607f821691505b60208210810361082c5761082b6107d2565b5b50919050565b7f466f7262696464656e0000000000000000000000000000000000000000000000600082015250565b60006108686009836105e8565b915061087382610832565b602082019050919050565b600060208201905081810360008301526108978161085b565b9050919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b6000600883026109007fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff826108c3565b61090a86836108c3565b95508019841693508086168417925050509392505050565b6000819050919050565b600061094761094261093d84610377565b610922565b610377565b9050919050565b6000819050919050565b6109618361092c565b61097561096d8261094e565b8484546108d0565b825550505050565b600090565b61098a61097d565b610995818484610958565b505050565b5b818110156109b9576109ae600082610982565b60018101905061099b565b5050565b601f8211156109fe576109cf8161089e565b6109d8846108b3565b810160208510156109e7578190505b6109fb6109f3856108b3565b83018261099a565b50505b505050565b600082821c905092915050565b6000610a2160001984600802610a03565b1980831691505092915050565b6000610a3a8383610a10565b9150826002028217905092915050565b610a53826103d7565b67ffffffffffffffff811115610a6c57610a6b61065e565b5b610a768254610801565b610a818282856109bd565b600060209050601f831160018114610ab45760008415610aa2578287015190505b610aac8582610a2e565b865550610b14565b601f198416610ac28661089e565b60005b82811015610aea57848901518255600182019150602085019450602081019050610ac5565b86831015610b075784890151610b03601f891682610a10565b8355505b6001600288020188555050505b50505050505056fea264697066735822122099912718e627af7e371482a1563a2f74be2babd306eb119d8a3dd0758f37f41864736f6c63430008130033";

    public static final String FUNC_GETHUELLA = "getHuella";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_POSITIONS = "positions";

    public static final String FUNC_SETHUELLA = "setHuella";

    public static final String FUNC_TRANSPORTID = "transportId";

    @Deprecated
    protected Huella_sol_Huella(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Huella_sol_Huella(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Huella_sol_Huella(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Huella_sol_Huella(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<List> getHuella() {
        final Function function = new Function(FUNC_GETHUELLA,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> positions(BigInteger param0) {
        final Function function = new Function(FUNC_POSITIONS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setHuella(String _newPos) {
        final Function function = new Function(
                FUNC_SETHUELLA,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_newPos)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> transportId() {
        final Function function = new Function(FUNC_TRANSPORTID,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Huella_sol_Huella load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Huella_sol_Huella(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Huella_sol_Huella load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Huella_sol_Huella(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Huella_sol_Huella load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Huella_sol_Huella(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Huella_sol_Huella load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Huella_sol_Huella(contractAddress, web3j, transactionManager, contractGasProvider);
    }
/*
    public static RemoteCall<Huella_sol_Huella> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner),
                new org.web3j.abi.datatypes.generated.Uint256(_transportId)));
        return deployRemoteCall(Huella_sol_Huella.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }
*/
    public static RemoteCall<Huella_sol_Huella> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _owner, BigInteger _transportId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner),
                new org.web3j.abi.datatypes.generated.Uint256(_transportId)));
        return deployRemoteCall(Huella_sol_Huella.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Huella_sol_Huella> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _owner, BigInteger _transportId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner),
                new org.web3j.abi.datatypes.generated.Uint256(_transportId)));
        return deployRemoteCall(Huella_sol_Huella.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Huella_sol_Huella> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _owner, BigInteger _transportId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner),
                new org.web3j.abi.datatypes.generated.Uint256(_transportId)));
        return deployRemoteCall(Huella_sol_Huella.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
