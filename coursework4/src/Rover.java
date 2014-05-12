import java.util.ArrayList;

public class Rover {
  private ArrayList<Asset> assets;
  private ArrayList<Response> log;

  public Rover() {
    //init assets and log
    log = new ArrayList<Response>();
    assets = new ArrayList<Asset>();
    //named the assets as the commands they should handle see receiveCommand why
    assets.add(new ThermometerAsset("GetTemperatureCommand"));
    assets.add(new SteererAsset("MakeRotateCommand"));
    assets.add(new DriveAsset("MakeMoveCommand"));
    assets.add(new GPSAsset("GetLocationCommand"));
    assets.add(new BarometerAsset("GetPressureCommand"));
  }
  
  public void log(Response response) {
    //log response
    log.add(response);
  }
  
  public Response getLastResponse() {
    //getter for last response
    return log.get(log.size()-1);
  }
  
  public ArrayList<Response> getLog() {
    return log;
  }

  //wanted to check if log works
  /* public void printLog() {
  //   for (Response entry : log) {
  //     System.out.println(entry);
  //   }
  // }*/

  public Response receiveCommand(Command command) {
    Response response = null;
    int i = 0;
    while(response == null) {
      Asset asset = assets.get(i);
      //check if the name of the command is the same as the asset
      if (asset.getName() == command.getClass().getName()) {
        //execute if it is
        response = asset.execute(command);
      }
      i++;
    }
    log(response);
    return response;
  }
}