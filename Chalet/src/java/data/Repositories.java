/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author frederic.vlummens
 */
public class Repositories
{
    private static final LedenRepository ledenRepo = new MySqlLedenRepository();
    private static final InventarisRepository invRepo = new MySqlInventarisRepository();
    private static final FileLog fileLog = new FileLog();
    
    private Repositories()
    {
    }
    
    public static LedenRepository getLedenRepository()
    {
        return ledenRepo;
    }
    
    public static InventarisRepository getInventarisRepository()
    {
        return invRepo;
    }
    
    public static FileLog getFileLog() {
        return fileLog;
    }
    
}
