package com.knoldus.file_read.hello.impl;

import akka.Done;
import akka.NotUsed;
import akka.japi.Pair;
import com.knoldus.file_read.hello.api.ResultPojo;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.ResponseHeader;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import com.knoldus.file_read.hello.api.HelloService;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;

/**
 * Implementation of the HelloService.
 */
public class HelloServiceImpl implements HelloService {

    //private final PersistentEntityRegistry persistentEntityRegistry;
    private final FileHandle fileHandle;

  @Inject
  public HelloServiceImpl(FileHandle fileHandle) {
    this.fileHandle = fileHandle;
  }

  @Override
  public HeaderServiceCall<NotUsed, List<ResultPojo>> getResult(String id) {
    return (requestHeader,response) -> {
      return CompletableFuture.completedFuture(new Pair(ResponseHeader.OK,(fileHandle.retrieveData())));
    };
  }
  /*private ResultPojo getResult(String st1){
    ResultPojo resultPojo = new ResultPojo();
    resultPojo.setArrayList(fileHandle.rerieveData());
    return resultPojo;
  }*/
}
