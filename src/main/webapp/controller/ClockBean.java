package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.rest.ws.rest.Clock;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import org.primefaces.event.SelectEvent;

@SuppressWarnings("deprecation")
@ManagedBean
public class ClockBean {

	private Date time;
	private Date time2;

	private float angle;
	private static String root = "http://localhost:8080/rest/rest/clock/";

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
						format.format(event.getObject())));
	}

	public void click() {
		
		String hdtime = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("time2");

		Calendar calendar = Calendar.getInstance();
		if (this.getTime() != null){
			calendar.setTime(this.getTime());
			hdtime = "";
		}
			
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		if (hdtime != "") {
			String time = hdtime.split(" ")[3];
			hours = Integer.parseInt(time.split(":")[0]);
			minutes = Integer.parseInt(time.split(":")[1]);
		}

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
				Boolean.TRUE);
		Client client = Client.create(clientConfig);
		String u = String.format("%s%s/%s", ClockBean.root, hours, minutes);
		WebResource webResource = client.resource(u);
		ClientResponse resp = webResource.type("application/json").get(
				ClientResponse.class);
		Clock clock = resp.getEntity(Clock.class);
		this.setAngle(clock.getAngle());
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

}