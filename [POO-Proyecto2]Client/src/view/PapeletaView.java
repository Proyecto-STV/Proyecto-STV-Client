/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Nelson
 */
public class PapeletaView extends javax.swing.JPanel {

    private CandidateView [][] candidateView;
    private CandidateView [][] candidateSelected;
    
    /**
     * Creates new form PapeletaView
     * @param candidateViews
     */
    public PapeletaView(CandidateView[][] candidateViews) {
        initComponents();        
        this.candidateView = candidateViews;
        this.candidateSelected = new CandidateView[this.candidateView.length][this.candidateView[0].length];
        init();        
    }
    
    private void init(){
        for (int i = 0; i < candidateView.length; i++) {
            for (int j = 0; j < candidateView[0].length; j++) {
                panelCandidates.add(candidateView[i][j]).setBounds(i * 145, j * 315, 145, 315);
            }
            updateUI();
            repaint();
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panelCandidates = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelCandidatesSelected = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        panelCandidates.setBackground(new java.awt.Color(255, 255, 255));
        panelCandidates.setName(""); // NOI18N
        panelCandidates.setPreferredSize(null);
        panelCandidates.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCandidatesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelCandidatesLayout = new javax.swing.GroupLayout(panelCandidates);
        panelCandidates.setLayout(panelCandidatesLayout);
        panelCandidatesLayout.setHorizontalGroup(
            panelCandidatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );
        panelCandidatesLayout.setVerticalGroup(
            panelCandidatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelCandidates);

        panelCandidatesSelected.setBackground(new java.awt.Color(255, 255, 255));
        panelCandidatesSelected.setPreferredSize(new java.awt.Dimension(435, 630));
        panelCandidatesSelected.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCandidatesSelectedMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelCandidatesSelectedLayout = new javax.swing.GroupLayout(panelCandidatesSelected);
        panelCandidatesSelected.setLayout(panelCandidatesSelectedLayout);
        panelCandidatesSelectedLayout.setHorizontalGroup(
            panelCandidatesSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );
        panelCandidatesSelectedLayout.setVerticalGroup(
            panelCandidatesSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(panelCandidatesSelected);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void addCandidateSelected(CandidateView candidate) {
        for (int i = 0; i < candidateSelected.length; i++) {
            for (int j = 0; j < candidateSelected[0].length; j++) {
                if (candidateSelected[i][j] == null){
                    candidateSelected[i][j] = candidate;
                    return;
                }
            }
        }        
    }
    
    private void fillSelectedPanelCandidates(){
        for (int i = 0; i < candidateSelected.length; i++) {
            for (int j = 0; j < candidateSelected[0].length; j++) {
                if (candidateSelected[i][j] != null)
                    panelCandidatesSelected.add(candidateSelected[i][j]).setBounds(i * 145, j * 315, 145, 315);
            }
        }
    }
    
    private void panelCandidatesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCandidatesMouseClicked
        int i = evt.getX()/145;
        int j = evt.getY()/315;        
        addCandidateSelected(candidateView[i][j]); 
        fillSelectedPanelCandidates();
        panelCandidates.repaint();
        panelCandidates.updateUI();
        panelCandidatesSelected.repaint();
        panelCandidatesSelected.updateUI();
    }//GEN-LAST:event_panelCandidatesMouseClicked

    private void panelCandidatesSelectedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCandidatesSelectedMouseClicked
        int i = evt.getX()/145;
        int j = evt.getY()/315;
        panelCandidates.add(candidateView[i][j]);
        panelCandidates.repaint();
        panelCandidates.updateUI();
        panelCandidatesSelected.repaint();
        panelCandidatesSelected.updateUI();
    }//GEN-LAST:event_panelCandidatesSelectedMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelCandidates;
    private javax.swing.JPanel panelCandidatesSelected;
    // End of variables declaration//GEN-END:variables
}
