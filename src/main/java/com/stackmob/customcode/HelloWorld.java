/**
 * Copyright 2012-2013 StackMob
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stackmob.customcode;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import com.stackmob.core.customcode.CustomCodeMethod;
import com.stackmob.core.rest.ProcessedAPIRequest;
import com.stackmob.core.rest.ResponseToProcess;
import com.stackmob.sdkapi.SDKServiceProvider;

import java.net.HttpURLConnection;
import java.util.*;

import com.stackmob.sdkapi.http.Header;
import com.stackmob.sdkapi.http.HttpService;
import com.stackmob.sdkapi.http.request.GetRequest;
import com.stackmob.sdkapi.http.response.HttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HelloWorld implements CustomCodeMethod {

  @Override
  public String getMethodName() {
    return "hello_world";
  }

  @Override
  public List<String> getParams() {
    return Arrays.asList("accessToken");
  }

  @Override
  public ResponseToProcess execute (ProcessedAPIRequest request, SDKServiceProvider serviceProvider) {
      Map<String, Object> map = new HashMap<String, Object>();

      String accessToken = "";
      JSONParser parser = new JSONParser();
      try {
          Object obj = parser.parse(request.getBody());
          JSONObject jsonObject = (JSONObject) obj;
          accessToken = (String) jsonObject.get("accessToken");
      } catch (ParseException pe) {

      }

      String url = "http://www.httpbin.org/get";

      // Formulate request headers
      Header accept = new Header("Accept-Charset", "utf-8");
      Header content = new Header("Content-Type", "application/x-www-form-urlencoded");

      Set<Header> set = new HashSet();
      set.add(accept);
      set.add(content);

      try {
          HttpService http = serviceProvider.getHttpService();

      /* In this Example we are going to be making a GET request
       * but PUT/POST/DELETE requests are also possible.
       */
          GetRequest req = new GetRequest(url,set);
          HttpResponse resp = http.get(req);

          int responseCode = resp.getCode();
          String responseBody = resp.getBody();

      } catch (Exception e) {}

      map.put("msg", "hello");


      return new ResponseToProcess(HttpURLConnection.HTTP_OK, map);
  }

}
