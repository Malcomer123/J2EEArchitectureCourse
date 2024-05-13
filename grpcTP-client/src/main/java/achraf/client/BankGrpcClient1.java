package achraf.client;

import achraf.stubs.Bank;
import achraf.stubs.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BankGrpcClient1 {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 5555)
                .usePlaintext()
                .build();

        BankServiceGrpc.BankServiceBlockingStub blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
        Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                .setAmount(100)
                .setFrom("MAD")
                .setTo("USD")
                .build();

        Bank.ConvertCurrencyResponse currencyResponse = blockingStub.convert(request);

        System.out.println(currencyResponse);
    }
}
