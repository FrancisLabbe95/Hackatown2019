package hello;

public class GoogleHomeHelper {

  private final int userId = 1; // TODO with auth in future release
  private final int defaultQuantity = 6;
  private final double defaultAvailableTime = 1;
  private final String defaultAvailableUnit = "h";
  private UserService service = new UserService();
  private User googleHomeUser = service.get(userId);

  public GoogleHomeHelper(){
  }

  public void addBottles(){
    addBottles(defaultQuantity);
  }
  public void addBottles(int quantity){
    int currentStock = googleHomeUser.getStock();
    int parsedQuantity = quantityParser(quantity + currentStock);

    googleHomeUser.setStock(parsedQuantity);
    service.update(googleHomeUser);
  }
  public void removeBottles(){
    removeBottles(defaultQuantity);
  }
  public void removeBottles(int quantity){
    int currentStock = googleHomeUser.getStock();
    int parsedQuantity = quantityParser(currentStock - quantity);
    googleHomeUser.setStock(parsedQuantity);
    service.update(googleHomeUser);
  }
  public void setAvailable(){
    setAvailable(defaultAvailableTime, defaultAvailableUnit );
  }
  public void setAvailable(double duration, String unit){
    googleHomeUser.setAvailability(true); //todo implement a dateTime in database
    service.update(googleHomeUser);
  }
  public void setUnavailable(){
    googleHomeUser.setAvailability(false); //todo implement a dateTime in database
    service.update(googleHomeUser);
  }

  private int quantityParser(int quantity){
    int result = 0;
    if(quantity <=0){
      result = 0;
    }
    if(quantity <= 6){
      result = 6;
    }
    if(quantity <=12){
      result = 12;
    }
    if(quantity <=24){
      result = 24;
    }
    if(quantity <=48){
      result = 48;
    }
    else{
      result = 49;
    }
    return result;
  }
}
