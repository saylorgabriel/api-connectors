package com.wordnik.client.api;

import com.wordnik.client.ApiException;
import com.wordnik.client.ApiInvoker;
import com.wordnik.client.model.Error;
import com.wordnik.client.model.Execution;
import java.util.*;
import java.io.File;

public class ExecutionApi {
  String basePath = "https://www.bitmex.com/api/v1";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public void addHeader(String key, String value) {
    getInvoker().addDefaultHeader(key, value);
  }

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }

  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  public String getBasePath() {
    return basePath;
  }

  //error info- code: 200 reason: "Request was successful" model: <none>
  //error info- code: 400 reason: "Parameter Error" model: Error
  //error info- code: 401 reason: "Unauthorized" model: Error
  //error info- code: 404 reason: "Not Found" model: Error
  public List<Execution> getMyExecutions (Object filter, Double count) throws ApiException {
    // create path and map variables
    String path = "/execution".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    if(!"null".equals(String.valueOf(filter)))
      queryParams.put("filter", String.valueOf(filter));
    if(!"null".equals(String.valueOf(count)))
      queryParams.put("count", String.valueOf(count));
    String contentType = "application/json";

    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams, contentType);
      if(response != null){
        return (List<Execution>) ApiInvoker.deserialize(response, "List", Execution.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      if(ex.getCode() == 404) {
        return null;
      }
      else {
        throw ex;
      }
    }
  }
  }
