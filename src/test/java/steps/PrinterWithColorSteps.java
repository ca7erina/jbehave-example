package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

public class PrinterWithColorSteps {
  
  
  @Given("I have a color printer")
  public void createPrinter() {
    System.out.println(".....created a colorful printer");
    
  }
  
  
  @Then("I can print colorful")
  public void print() {
    System.out.println("....Now Printing colorful: Hello World!");
  }
}
