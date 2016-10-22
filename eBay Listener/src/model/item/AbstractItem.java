package model.item;

import java.net.URL;

public abstract class AbstractItem implements ItemPage{
	public Boolean found=null;
	URL site=null;
	Integer itemNumber=0;
	

	@Override
	public Boolean isFound() {
		return found;
	}

	@Override
	public void setSite(URL site) {
		this.site = site;
	}

	@Override
	public URL getSite() {
		return site;
	}

	@Override
	public void setItemNumber(int itemNum) {
		this.itemNumber = itemNum;
		
	}

	@Override
	public Integer getItemNumber() {
		return itemNumber;
	}

	@Override
	public void setFound(Boolean isItemFound) {
		this.found = new Boolean(isItemFound);
		
	}
}
