package com.rest.ws.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.ws.rest.Clock;

@Path("/clock")
public class ServiceCalculateAngle {
	@GET
	@Path("/{hour}/{min}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Clock getAngle(@PathParam("hour") int hour, @PathParam("min") int min) {
		Clock c = new Clock();
		c.setMinute(min);
		c.setHour(hour >= 12 ? hour - 12 : hour);
		c.setAngle(0);

		float adt = (float) ((c.getMinute() / 5) * 2.5);
		float angle = ((c.getHour() * 60 + adt) - (12 * c.getMinute())) / 2;
		angle = angle < 0 ? angle * -1 : angle;
		c.setAngle(angle > 180 ? 360 - angle : angle);
		return c;
	}
	@GET
	@Path("/{hour}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Clock getAngle(@PathParam("hour") int hour) {
		Clock c = new Clock();
		c.setHour(hour >= 12 ? hour - 12 : hour);
		c.setAngle(0);

		float angle = (c.getHour() * 60) - (12 * c.getMinute()) / 2;
		angle = angle < 0 ? angle * -1 : angle;
		c.setAngle(angle > 180 ? 360 - angle : angle);
		return c;
	}
	
}
