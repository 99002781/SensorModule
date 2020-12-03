package io.tpd.rabbitmqexample;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PracticalTipSender {

    private static final Logger log = LoggerFactory.getLogger(PracticalTipSender.class);

    private final RabbitTemplate rabbitTemplate;

    public PracticalTipSender(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void schedule()
	{
	 generateRandom();
	 generateHeartRateRandom();
	 generateOLRandom();
	}
   
	@Scheduled(fixedDelay = 3000L)
   
        //PracticalTipMessage tip = new PracticalTipMessage("Always use Immutable classes in Java", 1, false);
    	public void generateRandom() {
   		 
   		 int minbp=90;
   		 int maxbp=140;
			/*
			 * int minol=60; int maxol=100; int minhr=40; int maxhr=100;
			 */
   		 Random random = new Random();
   		 Integer patientid = 1;
   		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");  
   	    Date date = new Date();  
		/* return random.nextInt(max-min)+min; */
   		  //Integer blood_pressure = random.nextInt(max-min)+min;
   		 /*System.out.print("BP:");
   		 System.out.println( blood_pressure);*/
   		PracticalTipMessage tip = new PracticalTipMessage(patientid,formatter.format(date),random.nextInt(maxbp-minbp)+minbp, null, null);
        rabbitTemplate.convertAndSend(RabbitmqExampleApplication.EXCHANGE_NAME, RabbitmqExampleApplication.Blood_Pressure, tip);
        log.info("Practical Tip sent");
    }
	@Scheduled(fixedRate=5000)
	 public void generateHeartRateRandom() {
	 
		int minhr=40; int maxhr=100;
	 Random random = new Random();
	 Integer patientid = 1;
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");  
	    Date date = new Date(); 
	 /*
	 * return (random.nextInt(max-min)+min) ;
	 */
	    PracticalTipMessage tip = new PracticalTipMessage(patientid,formatter.format(date),null, null, random.nextInt(maxhr-minhr)+minhr);
        rabbitTemplate.convertAndSend(RabbitmqExampleApplication.EXCHANGE_NAME, RabbitmqExampleApplication.Heart_rate, tip);
        log.info("Practical Tip sent");
	 } 
	@Scheduled(fixedRate=8000)
	 public void generateOLRandom() {
	 
		int minol=60;
		int maxol=100; 
	 Random random = new Random();
	 Integer patientid = 1;
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");  
    Date date = new Date(); 
    PracticalTipMessage tip = new PracticalTipMessage(patientid,formatter.format(date),null, random.nextInt(maxol-minol)+minol, null);
    rabbitTemplate.convertAndSend(RabbitmqExampleApplication.EXCHANGE_NAME, RabbitmqExampleApplication.Blood_oxygen_level, tip);
    log.info("Practical Tip sent");
	 
}
}
