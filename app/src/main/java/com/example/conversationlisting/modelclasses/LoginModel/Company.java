
package com.example.conversationlisting.modelclasses.LoginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("company_id")
    @Expose
    private Integer companyId;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("company_number")
    @Expose
    private String companyNumber;
    @SerializedName("is_logged_into")
    @Expose
    private Boolean isLoggedInto;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public Boolean getIsLoggedInto() {
        return isLoggedInto;
    }

    public void setIsLoggedInto(Boolean isLoggedInto) {
        this.isLoggedInto = isLoggedInto;
    }

}
