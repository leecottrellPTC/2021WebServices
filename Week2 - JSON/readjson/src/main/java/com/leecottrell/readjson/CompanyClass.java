package com.leecottrell.readjson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Generated("jsonschema2pojo")
public class CompanyClass {

private String company;
private String industry;
private List<String> phone = null;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public CompanyClass() {
}

/**
*
* @param phone
* @param company
* @param industry
*/
public CompanyClass(String company, String industry, List<String> phone) {
super();
this.company = company;
this.industry = industry;
this.phone = phone;
}

public String getCompany() {
return company;
}

public void setCompany(String company) {
this.company = company;
}

public CompanyClass withCompany(String company) {
this.company = company;
return this;
}

public String getIndustry() {
return industry;
}

public void setIndustry(String industry) {
this.industry = industry;
}

public CompanyClass withIndustry(String industry) {
this.industry = industry;
return this;
}

public List<String> getPhone() {
return phone;
}

public void setPhone(List<String> phone) {
this.phone = phone;
}

public CompanyClass withPhone(List<String> phone) {
this.phone = phone;
return this;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

public CompanyClass withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(CompanyClass.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("company");
sb.append('=');
sb.append(((this.company == null)?"<null>":this.company));
sb.append(',');
sb.append("industry");
sb.append('=');
sb.append(((this.industry == null)?"<null>":this.industry));
sb.append(',');
sb.append("phone");
sb.append('=');
sb.append(((this.phone == null)?"<null>":this.phone));
sb.append(',');
sb.append("additionalProperties");
sb.append('=');
sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
sb.append(',');
if (sb.charAt((sb.length()- 1)) == ',') {
sb.setCharAt((sb.length()- 1), ']');
} else {
sb.append(']');
}
return sb.toString();
}

}
/*package com.leecottrell.readjson;

import java.util.List;

public class CompanyClass {
    private String company;
    private String industry;
    private List<String> phone;
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public List<String> getPhone() {
        return phone;
    }
    public void setPhones(List<String> phone) {
        this.phone = phone;
    }
    public CompanyClass() {
    }


    public CompanyClass(String company, String industry, List<String> phone) {
        this.company = company;
        this.industry = industry;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{" +
            " company='" + getCompany() + "'" +
            ", industry='" + getIndustry() + "'" +
            ", phones='" + getPhone() + "'" +
            "}";
    }
    
}
*/