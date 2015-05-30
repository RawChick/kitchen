package domain;

public class ProductIngredients {
	private int productNr;
	private Product product;
	private Ingredient ingredient;
	private int amount;
	private String unit;

	public ProductIngredients(int productNr, Product product, Ingredient ingredient, int amount, String unit){
		this.productNr = productNr; 
		this.product = product;
		this.ingredient = ingredient;
		this.amount = amount;
		this.unit = unit;
	
	}
	
	public int getProductNr(){
		return productNr;
	}
	
	public int getIngredientNr(){
		return ingredient.getIngredientNr();
	}
	
	
	public int getAmount(){
		return amount;
	}
	
	public String getUnit(){
		return unit;
	}
	

	
	
}
