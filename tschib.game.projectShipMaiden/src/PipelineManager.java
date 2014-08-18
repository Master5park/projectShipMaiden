import java.util.ArrayList;

public class PipelineManager {
	ArrayList<Asset> guiTop5;
	ArrayList<Asset> guiBottom4;
	ArrayList<Asset> itemTop3;
	ArrayList<Asset> itemBottom2;
	ArrayList<Asset> bg1;

	PipelineManager() {
		guiTop5 = new ArrayList<Asset>();
		guiBottom4 = new ArrayList<Asset>();
		itemTop3 = new ArrayList<Asset>();
		itemBottom2 = new ArrayList<Asset>();
		bg1 = new ArrayList<Asset>();
	}

	public void addAsset(Asset newAsset, int layer) {
		switch (layer) {
		case 1:
			bg1.add(newAsset);
			break;
		case 2:
			itemBottom2.add(newAsset);
			break;
		case 3:
			itemTop3.add(newAsset);
			break;
		case 4:
			guiBottom4.add(newAsset);
			break;
		case 5:
			guiTop5.add(newAsset);
			break;
		}
	}
}