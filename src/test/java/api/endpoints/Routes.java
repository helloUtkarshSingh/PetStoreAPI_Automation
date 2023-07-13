package api.endpoints;

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//User module
	public static String post_user_url = base_url+"/user";
	public static String get_user_url = base_url+"/user/{username}";
	public static String update_user_url = base_url+"/user/{username}";
	public static String delete_user_url = base_url+"/user/{username}";
	
	//Store module
	public static String post_order_url = base_url+"/store/order";
	public static String get_order_url = base_url+"/store/order/{order_id}";
	public static String get_inventory_url = base_url+"/store/inventory";
	public static String delete_order_url = base_url+"/store/order/{order_id}";

}
