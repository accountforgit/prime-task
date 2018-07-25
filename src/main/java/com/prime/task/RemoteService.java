package com.prime.task;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("fileService")
class FileRemoteService implements RemoteService{

    @Override
    public String getData() {
        return "1";
    }
}

@Service("apiService")
class ApiRemoteService implements RemoteService{

    @Override
    public String getData() {
        return "2";
    }
}

public interface RemoteService {
    public String getData();
}
