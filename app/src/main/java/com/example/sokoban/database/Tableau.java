package com.example.sokoban.database;

import java.io.Serializable;

public class Tableau implements Serializable {

        private int tableauId;
        private String level;
        private int order;

        public Tableau()  {

        }

        public Tableau(String level) {
            this.level = level;
        }

        public Tableau(int tableauId, String level, int order) {
            this.tableauId = tableauId;
            this.level = level;
            this.order = order;
        }

        public int getTableauId() {
            return tableauId;
        }

        public void setLevel(String level) {
            this.level = level;
        }
        public String getLevel() {
            return level;
        }

        public void setOrder(int order) {
            this.order = order;
        }


        public int getOrder() {
            return order;
        }
}
