/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

/**
 *
 * @author internet
 */
public class Funcionario {
    
    
    private int id;
    private String nome;
    private String cargo;
    private int numeroDependentes;
    private float salario;
    private float salarioComDescontoINSS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return cargo;
    }

    public void setCategoria(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getNumeroDependentes() {
        return numeroDependentes;
    }

    public void setNumeroDependentes(int numeroDependentes) {
        this.numeroDependentes = numeroDependentes;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getSalarioComDescontoINSS() {
        
        
          if (this.salario <= 1693.72){
            
            salarioComDescontoINSS = (this.salario * 0.92f);
            
        } else if (this.salario > 1693.72 && this.salario <=2822.9){
            
            salarioComDescontoINSS = (this.salario * 0.91f);
        }else if (this.salario > 2822.9 && this.salario <= 5645.8){
            
            salarioComDescontoINSS = (this.salario * 0.89f);
        }else {
            
            salarioComDescontoINSS = (this.salario - 621.04f);
            
            
        }

         
        
        return salarioComDescontoINSS;
    }

    public void setSalarioComDescontoINSS(float salarioComDescontoINSS) {
        this.salarioComDescontoINSS = salarioComDescontoINSS;
    }


  
    
}
