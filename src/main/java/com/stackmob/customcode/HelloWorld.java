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

import com.stackmob.core.customcode.CustomCodeMethod;
import com.stackmob.core.rest.ProcessedAPIRequest;
import com.stackmob.core.rest.ResponseToProcess;
import com.stackmob.sdkapi.SDKServiceProvider;

import java.net.HttpURLConnection;
import java.util.*;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.conf.ConfigurationBuilder;


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

      ConfigurationBuilder cb = new ConfigurationBuilder();
      cb.setDebugEnabled(true)
              .setOAuthAppId("651101234913565")
              .setOAuthAppSecret("9eb8f1ddb07752885ff07e6d76ad7903")
              .setOAuthAccessToken(accessToken);

      Facebook facebook = new FacebookFactory(cb.build()).getInstance();
      // ResponseList<Friend> myFriends = null;
      // try {
      //     myFriends = facebook.getFriends();
      // }
      // catch (Exception e) {

      // }
      map.put("msg", "hello");


      return new ResponseToProcess(HttpURLConnection.HTTP_OK, map);
  }

}
