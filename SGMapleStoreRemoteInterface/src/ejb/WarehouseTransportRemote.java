/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface WarehouseTransportRemote {

    public List<Vector> viewInvoiceList();
    
}
