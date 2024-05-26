//Airport Class
public class Airport
{
    String code;

    String name;

    String country;

    String city;


    public Airport(String Code, String Name, String City, String Country)
    {
        code = Code;
        name = Name;
        city = City;
        country = Country;
    }
    public String get_airport_code()
    {
        return code;
    }
    public String get_airport_name()
    {
        return name;
    }
    public String get_airport_city()
    {
        return city;
    }
    public String get_airport_country()
    {
        return country;
    }

    public void set_airport_code(String NewCode){code = NewCode;}
    public void set_airport_name(String NewName){name = NewName;}
    public void set_airport_city(String CurrentCity){city = CurrentCity;}
    public void set_airport_country(String CurrentCountry){country = CurrentCountry;}


}
