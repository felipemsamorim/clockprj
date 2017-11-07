package com.rest.client;

import com.rest.ws.rest.Clock;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class ClientRest {
	public static void main(String args[]) {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		Client client = Client.create(clientConfig);
		String u = "http://localhost:8080/rest/clock/18/00";
		WebResource webResource = client.resource(u);
		ClientResponse resp = webResource.type("application/json")
								.get(ClientResponse.class);
		Clock clock = resp.getEntity(Clock.class);
		System.out.println(clock.getAngle());
	}
}
