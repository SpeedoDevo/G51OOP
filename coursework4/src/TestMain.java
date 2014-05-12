/*
 * simple mars rover control
 * @author: Barnabas Forgo (bxf03u, 4211949)
 */

public class TestMain {

  public static void main(String[] args) {
    //init
    Rover curiosity = new Rover();
    TestMain testMain = new TestMain();
    testMain.beginExploration(curiosity);
  }
  
  public void beginExploration(Rover curiosity) {
    //endless prompt loop
    while(true) {
      System.out.print(">");
      //get command
      Command command = generateCommand(G51OOPInput.readString().trim());
      //send command if not null
      if (command != null) {
        Response response = curiosity.receiveCommand(command);
        System.out.println(response);
      } 
      //just wanted to check if log works
      /*else {
        curiosity.printLog();
      }*/
    }
  }

  private Command generateCommand(String input) {
    String arg;
    Command command = null;
    //check for no input, return, reprompt
    if (input.length() == 0) {
      return null;
    }
    //huge switch statement that checks for the first char of input
    //note toLowerCase, makes life easier
    switch (input.toLowerCase().charAt(0)) {
      //commands with arguments
      case 't':
        arg = getArg(input);
        if (arg != null) {
          //switch for arguments
          switch (arg.toLowerCase()) {
            case "c":
              command = new GetTemperatureCommand(true);
              break;
            case "f":
              command = new GetTemperatureCommand(false);
              break;
            default:
              System.out.println("TestMain::generateCommand, parse error: incorrect temperature value " + arg);
              break;
          }
        }
        break;
      case 'm':
        arg = getArg(input);
        if (arg != null) {
          try {
            command = new MakeMoveCommand(Integer.valueOf(arg));
          } catch (NumberFormatException e) {
            //catch if numbar can't be parsed
            System.out.println("TestMain::generateCommand, parse error: incorrect move value " + arg);
          }
        }
        break;
      case 'r':
        arg = getArg(input);
        if (arg != null) {
          //switch for arguments
          switch (arg.toLowerCase()) {
            case "l":
              command = new MakeRotateCommand(Rotation.LEFT);
              break;
            case "r":
              command = new MakeRotateCommand(Rotation.RIGHT);
              break;
            case "a":
              command = new MakeRotateCommand(Rotation.AROUND);
              break;
            default:
              System.out.println("TestMain::generateCommand, parse error: incorrect rotation type " + arg);
              break;
          }
        }
        break;
      //one char commands
      case 'p':
        if (checkValid(input)) {
          command = new GetPressureCommand();
        } else {
          System.out.println("TestMain::generateCommand, parse error: incorrect command length");
        }
        break;
      case 'l':
        if (checkValid(input)) {
          command = new GetLocationCommand();
        } else {
          System.out.println("TestMain::generateCommand, parse error: incorrect command length");
        }
        break;
      case 'q':
        if (checkValid(input)) {
          System.out.println("Quitting");
          System.exit(0);
        } else {
          System.out.println("TestMain::generateCommand, parse error: incorrect command length");
        }
        break;
      default:
        System.out.println("TestMain::generateCommand, parse error: incorrect command option");
        break;
    }
    
    return command;
  }

  private String getArg(String input) {
    try {
      if (input.charAt(1) != ' ') {
        //check if there is space between command and args
        System.out.println("TestMain::generateCommand, parse error: incorrect command option");
        return null;
      } else {
        return input.substring(2);
      }
    } catch (IndexOutOfBoundsException e) {
      //catch no arguments
      System.out.println("TestMain::generateCommand, parse error: incorrect command length");
      return null;
    }
  }

  private boolean checkValid(String input) {
    //check if the command is one char long
    return !(input.length() > 1);
  }
}
