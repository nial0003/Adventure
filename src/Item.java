public class Item {
    private String itemName;
    private String itemDescription;
    private boolean isWeapon;

    public Item(String name, String description, boolean isWeapon) {
        this.itemName = name;
        this.itemDescription = description;
        this.isWeapon = isWeapon;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public boolean isWeapon(){
        return isWeapon;
    }

}
