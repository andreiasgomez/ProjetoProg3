package model;

public class Treinador extends Funcionario implements Treina {
    private static final long serialVersionUID = 1L;

    public Treinador(int id, String nome, double salario) {
        super(id, nome, salario);
    }

    @Override
    public void ensinarTecnologia() {
        System.out.println(getNome() + " está ensinando tecnologia.");
    }

    @Override
    public void motivarEquipe() {
        System.out.println(getNome() + " está motivando a equipe.");
    }

    @Override
    public String mostrarDetalhes() {
        return "Treinador: " + getNome() + ", Salário: " + getSalario();
    }
}
