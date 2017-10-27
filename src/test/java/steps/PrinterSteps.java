package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

public class PrinterSteps {
  
  
  @Given("I have a printer")
  public void createPrinter() {
    System.out.println(".....created a printer");
    
  }
  
  
  @Then("I can print")
  public void print() {
    System.out.println("....Now Printing: Hello World!");
  }
}
