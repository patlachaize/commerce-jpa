package ch.etml.pl.commerce;

public class CommerceException extends Exception {
  public CommerceException(Exception e) {
    super("Commerce exception" + e.getMessage());
  }
}
