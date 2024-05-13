package achraf.service;

import achraf.stubs.Bank;
import achraf.stubs.BankServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Timer;

public class BankGrpcService extends BankServiceGrpc.BankServiceImplBase{
    @Override
    public void convert(Bank.ConvertCurrencyRequest request, StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        String currencyFrom = request.getFrom();
        String currencyTo = request.getTo();
        float amount = request.getAmount();
        Bank.ConvertCurrencyResponse response = Bank.ConvertCurrencyResponse.newBuilder()
                .setAmount(amount)
                .setFrom(currencyFrom)
                .setTo(currencyTo)
                .setResult(amount*12.65F)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getCurrencyStream(Bank.ConvertCurrencyRequest request, StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        String currencyFrom = request.getFrom();
        String currencyTo = request.getTo();
        float amount = request.getAmount();

        Timer timer = new Timer();
        timer.schedule(new java.util.TimerTask() {
            int counter=0;
            @Override
            public void run() {
                Bank.ConvertCurrencyResponse response = Bank.ConvertCurrencyResponse.newBuilder()
                        .setAmount(amount)
                        .setFrom(currencyFrom)
                        .setTo(currencyTo)
                        .setResult(amount*12.65F)
                        .build();
                responseObserver.onNext(response);
                ++counter;
                if(counter==20){
                    responseObserver.onCompleted();
                    timer.cancel();
                }
            }
        }, 100, 1000);

    }

    @Override
    public StreamObserver<Bank.ConvertCurrencyRequest> performStream(StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        return new StreamObserver<Bank.ConvertCurrencyRequest>() {
            float sum = 0;
            @Override
            public void onNext(Bank.ConvertCurrencyRequest convertCurrencyRequest) {
                sum+=convertCurrencyRequest.getAmount();
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                Bank.ConvertCurrencyResponse response = Bank.ConvertCurrencyResponse.newBuilder()
                        .setResult(sum*12.65F)
                                .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }


    @Override
    public StreamObserver<Bank.ConvertCurrencyRequest> performBidirectionalStream(StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
        return new StreamObserver<Bank.ConvertCurrencyRequest>() {
            @Override
            public void onNext(Bank.ConvertCurrencyRequest convertCurrencyRequest) {
                Bank.ConvertCurrencyResponse response = Bank.ConvertCurrencyResponse.newBuilder()
                        .setAmount(convertCurrencyRequest.getAmount())
                        .setFrom(convertCurrencyRequest.getFrom())
                        .setTo(convertCurrencyRequest.getTo())
                        .setResult(convertCurrencyRequest.getAmount()*12.65F)
                        .build();

                responseObserver.onNext(response);

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
