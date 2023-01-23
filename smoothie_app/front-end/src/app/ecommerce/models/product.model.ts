import { NutritionValue } from "./nutrition-value.model";

// for storing product
export class Product {
    id: number;
    name: string;
    price: number;
    description: string;
    nutritionValue: NutritionValue;

    constructor(id: number, name: string, price: number, description: string) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}