package tr.com.otunctan.dp.behavioural.observer.exercise;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

interface Observer<T> {
    void handle(T result);
}


class Observable<T> {
    private List<Observer<T>> observers = new ArrayList<>();

    public void subscribe(Observer<T> observer) {
        this.observers.add(observer);
    }

    protected void onValueChange(T value) {
        for (Observer<T> obs : observers) {
            obs.handle(value);
        }
    }
}

public class HttpClient {
    public <T> Observable<T> get(String url, Object options) {
        return new HttClientObservable<T>().get(url, options);
    }

    public <T> Observable<T> post(String url, Object body, Object options) {
        return new HttClientObservable<T>().post(url, body, options);
    }

}

class HttClientObservable<T> extends Observable<T> {


    public Observable<T> post(String url, Object body, Object options) {
        doPost(url, body, options);
        return this;
    }

    private void doPost(String url, Object body, Object options) {
        // url connection get value
        //non blocking
        Callable<T> callable = new Callable<>() {
            @Override
            public T call() throws Exception {
                Thread.sleep(3000);
                ServiceResult<Object> objectServiceResult = new ServiceResult<>();
                onValueChange((T) objectServiceResult);
                return null;
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(callable);
        executorService.shutdown();
    }

    public Observable<T> get(String url, Object options) {
        return this;
    }
}


class ServiceResult<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

class Demo {

    public static void main(String[] args) {

        HttpClient httpClient = new HttpClient();
        Observable<ServiceResult<Object>> posted =//
                httpClient.<ServiceResult<Object>>post("www.google.com", null, null);

        posted.subscribe(data -> {
            System.out.println(data);
        });
        posted.subscribe(data -> {
            System.out.println(data);
        });
    }

}