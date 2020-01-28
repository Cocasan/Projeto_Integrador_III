/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Bean.Medicamento;
import PharmaDAO.MedDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MedicamentoTableModel {

    public class ProdutoTableModel extends AbstractTableModel {

        private List<Medicamento> medicamento = new ArrayList<>();
        private String[] colunas = {"Nome", "CNPJ", "Inscrição", "Bairro", "Número", "Complemento", "CEP", "Cidade", "Estado"};

        @Override
        public String getColumnName(int column) {
            return colunas[column];

        }

        @Override
        public int getRowCount() {
            return medicamento.size();

        }

        @Override
        public int getColumnCount() {
            return colunas.length;

        }

        @Override
        public Object getValueAt(int linha, int coluna) {
            switch (coluna) {
                case 0:
                    return medicamento.get(linha).getCOD_MED();
                case 1:
                    return medicamento.get(linha).getPRECO();
                case 2:
                    return medicamento.get(linha).getPRECO();
            }
            return null;

        }

        @Override
        public void setValueAt(Object valor, int linha, int coluna) {
            switch (coluna) {
                case 0:
                    medicamento.get(linha).setCOD_MED(Integer.parseInt((String) valor));
                    break;
                case 1:
                    medicamento.get(linha).setPRECO(Float.parseFloat((String) valor));
                    break;
                case 2:
                    medicamento.get(linha).setDES((String) valor);
                    break;
            }

            this.fireTableRowsUpdated(linha, linha);
        }

        public void addLinha(Medicamento a) {
            this.medicamento.add(a);
            this.fireTableDataChanged();
        }

        public void removeLinha(int linha) {
            this.medicamento.remove(linha);
            this.fireTableDataChanged();
        }

        public List<Medicamento> allData() {
            return medicamento;
        }

        public void lerDados() {
            MedDAO pdao = new MedDAO();
            pdao.read().forEach((p) -> {
                this.addLinha(p);
            });
            this.fireTableDataChanged();
        }

        public void recarregaTabela() {
            this.medicamento.clear();
            lerDados();
            this.fireTableDataChanged();
        }

        public Medicamento pegaDadosLinha(int linha) {
            return medicamento.get(linha);

        }
    }
}
