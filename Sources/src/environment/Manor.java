package environment;

import java.util.ArrayList;


public class Manor extends State {

//Attributes	
	private int length = 0;
	private int width = 0;
	private int posAspiratorX = 0;
	private int posAspiratorY = 0;
	private ArrayList<ArrayList<Boolean>> dustArray = null;
	private ArrayList<ArrayList<Boolean>> jewelryArray = null;

//Constructor	
	public Manor(int length, int width) {
		dustArray = new ArrayList<ArrayList<Boolean>>();
		jewelryArray = new ArrayList<ArrayList<Boolean>>();
		this.length = length;
		this.width = width;

		for (int index = 0; index < length; index++) {
			dustArray.add(new ArrayList<Boolean>());
			jewelryArray.add( new ArrayList<Boolean>());

			for (int indexW = 0; indexW < width; indexW++) {
				dustArray.get(index).add(false);
				jewelryArray.get(index).add(false);
			}
		}
	}

//Methods
	public void putDust(int x, int y) {
		if(x < length && y < width)
			dustArray.get(x).set(y, true);
	}

	public void putJewelry(int x, int y) {
		if(x < length && y < width)
			jewelryArray.get(x).set(y, true);
	}

	public boolean vacuumCleanerMoveRight() {
		if(posAspiratorX < length - 1) {
			++posAspiratorX;
			return true;
		}
		else
			return false;
	}

	public boolean vacuumCleanerMoveLeft() {
		if(posAspiratorX > 0) {
			--posAspiratorX;
			return true;
		}
		else
			return false;
	}

	public boolean vacuumCleanerMoveUp() {
		if(posAspiratorY > 0) {
			--posAspiratorY;
			return true;
		}
		else
			return false;
	}

	public boolean vacuumCleanerMoveDown() {
		if(posAspiratorY < width - 1) {
			++posAspiratorY;
			return true;
		}
		else
			return false;
	}

	/**
	 * 
	 * @return -2 if a jewelry is vacuum and they have no dust in the room
	 * @return -1 if a jewelry and dust is vacuum
	 * @return 0 if a nothing is vacuum
	 * @return 1 if they have just one dust vacuum
	 */
	public int vacuumCleanerVacuum() {
		if(jewelryArray.get(posAspiratorX).get(posAspiratorY) && dustArray.get(posAspiratorX).get(posAspiratorY)){
			dustArray.get(posAspiratorX).set(posAspiratorY, false);
			jewelryArray.get(posAspiratorX).set(posAspiratorY, false);
			return -1;
		}
		else if (jewelryArray.get(posAspiratorX).get(posAspiratorY) && !dustArray.get(posAspiratorX).get(posAspiratorY)) {
			jewelryArray.get(posAspiratorX).set(posAspiratorY, false);
			return -2;
		}
		else if (!jewelryArray.get(posAspiratorX).get(posAspiratorY) && dustArray.get(posAspiratorX).get(posAspiratorY)) {
			dustArray.get(posAspiratorX).set(posAspiratorY, false);
			return 1;
		}
		else if (!jewelryArray.get(posAspiratorX).get(posAspiratorY) && !dustArray.get(posAspiratorX).get(posAspiratorY)) {
			return 0;
		}
		return 0;
	}

	public boolean vacuumCleanerPickUpObject() {
		if(jewelryArray.get(posAspiratorX).get(posAspiratorY)){
			jewelryArray.get(posAspiratorX).set(posAspiratorY, false);
			return true;
		}
		else
			return false;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dustArray == null) ? 0 : dustArray.hashCode());
		result = prime * result + ((jewelryArray == null) ? 0 : jewelryArray.hashCode());
		result = prime * result + posAspiratorX;
		result = prime * result + posAspiratorY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manor other = (Manor) obj;
		if (dustArray == null) {
			if (other.dustArray != null)
				return false;
		} else if (!dustArray.equals(other.dustArray))
			return false;
		if (jewelryArray == null) {
			if (other.jewelryArray != null)
				return false;
		} else if (!jewelryArray.equals(other.jewelryArray))
			return false;
		if (posAspiratorX != other.posAspiratorX)
			return false;
		if (posAspiratorY != other.posAspiratorY)
			return false;
		return true;
	}

	@Override
	public Object clone(){
		Manor manor = (Manor) super.clone();
		manor.dustArray = (ArrayList<ArrayList<Boolean>>) dustArray.clone();
		manor.jewelryArray = (ArrayList<ArrayList<Boolean>>) jewelryArray.clone();
		for (int index = 0; index < manor.length; ++index) {
			manor.dustArray.set(index, (ArrayList<Boolean>) dustArray.get(index).clone());
			manor.jewelryArray.set(index, (ArrayList<Boolean>) jewelryArray.get(index).clone());
		}
		return manor;
	}

//Getters & setters
	public ArrayList<ArrayList<Boolean>> getDustArray() {
		return dustArray;
	}

	public ArrayList<ArrayList<Boolean>> getJewelryArray() {
		return jewelryArray;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public int getPosAspiratorX() {
		return posAspiratorX;
	}

	public int getPosAspiratorY() {
		return posAspiratorY;
	}

	public void setPosAspiratorX(int posAspiratorX) {
		this.posAspiratorX = posAspiratorX;
	}

	public void setPosAspiratorY(int posAspiratorY) {
		this.posAspiratorY = posAspiratorY;
	}

	public void setDustArray(ArrayList<ArrayList<Boolean>> dustArray) {
		this.dustArray = dustArray;
	}

	public void setJewelryArray(ArrayList<ArrayList<Boolean>> jewelryArray) {
		this.jewelryArray = jewelryArray;
	}

	public Boolean getDustAt(int x, int y) {
		return dustArray.get(x).get(y);
	}

	public Boolean getJewelryAt(int x, int y) {
		return jewelryArray.get(x).get(y);
	}
}
