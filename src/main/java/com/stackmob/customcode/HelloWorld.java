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

      FacebookClient facebookClient = new DefaultFacebookClient("CAAGF19ZAX3kUBAFpoaA9f2zY3ZARZAHxp15jZBZCmVeqC0Y8mvvbg48UZAZB8oZCPKezTrO2WANHkXBEqRaeowxvNov2CVBurZBJ0cfhKJ6e8KurZCFQLDZBkJxg0TqBZCZAGtz2ZB0KX4J6vd7VkQapOYxIkZCttJOjFAamPK2ZBRYJle0UEuKYiu5Wiy0LUZAIqSX69kjCWRektMgFprAZDZD");

      User user = facebookClient.fetchObject("me", User.class);


      // Facebook facebook = f.getInstance();
      
      // ResponseList<Friend> myFriends = null;
      // try {
      //     myFriends = facebook.getFriends();
      // }
      // catch (Exception e) {

      // }
      
      map.put("msg", user.getName());


      return new ResponseToProcess(HttpURLConnection.HTTP_OK, map);
  }

}
