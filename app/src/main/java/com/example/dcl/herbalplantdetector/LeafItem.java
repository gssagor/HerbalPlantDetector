package com.example.dcl.herbalplantdetector;
// created a global class for getting the image name and for image
public class LeafItem {
    String name;
    int image;
/// created a constructor
    public LeafItem(String name, int image) {
        this.name = name;
        this.image = image;
    }
/// used getter method for getting the name and images-
        public String getName() {
        return name;
    }
  
    public int getImage() {
        return image;
    }
}
