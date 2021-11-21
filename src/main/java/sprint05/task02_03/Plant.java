package sprint05.task02_03;

import sprint05.task02_03.Enums.Color;
import sprint05.task02_03.Enums.Type;
import sprint05.task02_03.Exceptions.ColorException;
import sprint05.task02_03.Exceptions.TypeException;

class Plant {
    private String name;
    private Color color;
    private Type type;

    public Plant(String type, String color, String name) {
        this.name = name;
        try {
            this.color = Color.valueOf(color.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new ColorException("Invalid value " + color + " for field color");
        }
        try{
            this.type = Type.valueOf(type.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new TypeException("Invalid value " + type + " for field type");
        }
    }

    public static Plant tryCreatePlant(String type, String color, String name){
        Plant plant = null;
        try {
            plant = new Plant(type, color, name);
        }catch (TypeException typeException){
            try {
                plant = new Plant("Ordinary", color, name);
            }catch (ColorException colorException){
                plant = new Plant("Ordinary", "Red", name);
            }
        } catch (ColorException exception){
            try {
                plant = new Plant(type, "Red", name);
            }catch (TypeException typeException){
                plant = new Plant("Ordinary", "Red", name);
            }
        }
        finally {
            return plant;
        }
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{type: " + type +
                ", color: " + color +
                ", name: " + name + "}";
    }
}






