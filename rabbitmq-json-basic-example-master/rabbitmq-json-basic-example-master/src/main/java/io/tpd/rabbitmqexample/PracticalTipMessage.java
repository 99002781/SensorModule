package io.tpd.rabbitmqexample;

import java.io.Serializable;

import java.util.Date;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;*/

import com.fasterxml.jackson.annotation.JsonProperty;
//@Entity
public class PracticalTipMessage {

	
	
	 
	    private int patientid=1;
    private final Integer oxygenlevel;
    private final Integer heartrate;
	private Integer bloodpressure;
	private String timestamp;
    public PracticalTipMessage(@JsonProperty("patientid") final Integer patientid,
    						   @JsonProperty("timestamp") final String timestamp,
    						   @JsonProperty("bloodpressure") final Integer bloodpressure,
                               @JsonProperty("bloodoxygenlevel") final Integer oxygenlevel,
                               @JsonProperty("heartrate") final Integer heartrate)
    {
    	this.patientid=patientid;
        this.timestamp=timestamp;
        this.bloodpressure=bloodpressure;
        this.heartrate = heartrate;
        this. oxygenlevel =  oxygenlevel;
        
    }
	public int getPatientid() {
		return patientid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public Integer getBloodpressure() {
		return bloodpressure;
	}
	
	
	
	public Integer getOxygenlevel() {
		return oxygenlevel;
	}
	public Integer getHeartrate() {
		return heartrate;
	}
	
}
   
