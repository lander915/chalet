/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author lander
 */
public class ChaletException extends RuntimeException{
    
    public ChaletException()
    {
    }
    
    public ChaletException(String message)
    {
        super(message);
    }
    
    public ChaletException(String message, Exception innerException)
    {
        super(message, innerException);
    }
}
