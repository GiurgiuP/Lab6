package Prob1;

import java.time.LocalDate;

public class Angajat
{
    private String numele;
    private String postul;
    private LocalDate data_angajarii;
    private float salariul;

    public Angajat(){}

    protected Angajat(String numele, String postul, LocalDate data_angajarii, float salariul)
    {
        this.numele = numele;
        this.postul = postul;
        this.data_angajarii = data_angajarii;
        this.salariul = salariul;
    }

    public String getNumele()
    {
        return numele;
    }
    public String getPostul()
    {
        return postul;
    }
    public LocalDate getData_angajarii()
    {
        return data_angajarii;
    }
    public float getSalariul()
    {
        return salariul;
    }

    public void setData_angajarii(LocalDate data_angajarii)
    {
        this.data_angajarii = data_angajarii;
    }
    public void setNumele(String numele)
    {
        this.numele = numele;
    }
    public void setPostul(String postul)
    {
        this.postul = postul;
    }
    public void setSalariul(float salariul)
    {
        this.salariul = salariul;
    }

    @Override
    public String toString()
    {
        return "\n"+"Numele:"+numele+"\n"+ "Postul:"+postul+"\n"+"Data angajarii:"+data_angajarii+"\n"+" Salariul:"+salariul+"\n";
    }


}
