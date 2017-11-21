package com.knoldus.file_read.hello.impl;
import akka.NotUsed;
import com.knoldus.file_read.hello.api.ResultPojo;
import com.lightbend.lagom.javadsl.api.transport.RequestHeader;
import com.lightbend.lagom.javadsl.api.transport.ResponseHeader;
import com.lightbend.lagom.javadsl.server.HeaderServiceCall;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.ws.util.CompletedFuture;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;
import akka.japi.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.Assert.assertEquals;

public class HelloServiceImplTest {
    @Injectable
    FileHandle fileHandle;
    @Tested
    HelloServiceImpl helloService;
    
    ResultPojo resultPojo = new ResultPojo();
    
    @Test
    public void getResultSuccessCase() throws Exception{
        new Expectations(){
            {
                List<ResultPojo> resultPojo =new ArrayList<ResultPojo>();
                ResultPojo resultPojo1 = new ResultPojo();
                ResultPojo resultPojo2 = new ResultPojo();
                
                resultPojo1.setOut("hi");
                resultPojo1.setMeraProperty("mukesh");
                resultPojo2.setOut("hi");
                resultPojo2.setMeraProperty("raj");
                resultPojo.add(resultPojo1);
                resultPojo.add(resultPojo2);
                
                fileHandle.retrieveData();
                returns(resultPojo);
        }};
        
        HeaderServiceCall headerServiceCall =helloService.getResult("mukesh");
        CompletionStage completionStage = headerServiceCall.invokeWithHeaders(RequestHeader.DEFAULT,NotUsed.getInstance());
       CompletableFuture future = completionStage.toCompletableFuture();
       Pair<ResponseHeader, ResultPojo> pair = (Pair<ResponseHeader, ResultPojo>) future.get();
    List<ResultPojo> resultPojo = (List<ResultPojo>) pair.second();
       String result1= resultPojo.get(0).getMeraProperty();
        String result2= resultPojo.get(1).getMeraProperty();
    
        assertEquals("this should be =pass", "mukesh", result1);
        assertEquals("this should be =pass", "raj", result2);
    }
}
