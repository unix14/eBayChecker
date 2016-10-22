package model.item;

import java.net.URL;

public interface ItemPage {
	
	public Boolean isFound();
	public void setFound(Boolean isItemFound);
	public void setSite(URL site);
	public URL getSite();
	public void setItemNumber(int itemNum);
	public Integer getItemNumber();
}
