package com.knoldus.file_read.hello.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import java.util.List;


public interface HelloService extends Service {
  //ServiceCall<NotUsed, String> getResult(String id);
  ServiceCall<NotUsed, List<ResultPojo>> getResult(String id);
  
  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("hellooutput").withCalls(Service.restCall(Method.GET,"/api/output/:id",  this::getResult)
      ).withAutoAcl(true);
  }
}
