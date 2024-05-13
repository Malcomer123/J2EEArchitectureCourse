package achraf.client;

import achraf.stubs.Bank;
import achraf.stubs.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Timer;

public class BankGrpcClient4 {
    public static void main(String[] args) throws IOException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 5555)
                .usePlaintext()
                .build();

        BankServiceGrpc.BankServiceStub asyncStub = BankServiceGrpc.newStub(managedChannel);
        Bank.ConvertCurrencyRequest request = Bank.ConvertCurrencyRequest.newBuilder()
                .setAmount(100)
                .setFrom("MAD")
                .setTo("USD")
                .build();
        StreamObserver<Bank.ConvertCurrencyRequest> performStream =
                asyncStub.performStream(new StreamObserver<Bank.ConvertCurrencyResponse>() {
            @Override
            public void onNext(Bank.ConvertCurrencyResponse convertCurrencyResponse) {
                System.out.println("***********");
                System.out.println(convertCurrencyResponse);
                System.out.println("***********");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("END");
            }
        });
        Timer timer = new Timer();
        timer.schedule(new java.util.TimerTask() {
            int counter=0;
            @Override
            public void run() {
                Bank.ConvertCurrencyRequest currencyRequest = Bank.ConvertCurrencyRequest.newBuilder()
                        .setAmount(100)
                        .setFrom("MAD")
                        .setTo("USD")
                        .build();
                performStream.onNext(request);
                ++counter;
                if(counter==20){
                    performStream.onCompleted();
                    timer.cancel();
                }
            }
        }, 100, 1000);
        System.out.println(".......?");
        System.in.read();
    }
}
