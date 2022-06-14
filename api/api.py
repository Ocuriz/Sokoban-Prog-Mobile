import flask
from flask import request, jsonify
import sqlite3
import json

app = flask.Flask(__name__)
app.config["DEBUG"] = True

def dict_factory(cursor, row):
   d = {}
   for idx, col in enumerate(cursor.description):
      d[col[0]] = row[idx]
   return d

@app.route('/api/all/tableau', methods=['GET'])
def getAllTableau():
   conn =sqlite3.connect('plateau.db')
   conn.row_factory = dict_factory
   cur = conn.cursor()
   all_tableau = cur.execute('SELECT * FROM tableau;').fetchall()

   return jsonify(all_tableau)

@app.route('/api/all/ligne', methods=['GET'])
def getAllLigne():
   conn =sqlite3.connect('plateau.db')
   conn.row_factory = dict_factory
   cur = conn.cursor()
   all_ligne = cur.execute('SELECT * FROM ligne;').fetchall()

   return jsonify(all_ligne)

@app.route('/api/tableau/<tableau_id>', methods=['GET'])
def getTableauByOrdre(tableau_id):
   conn =sqlite3.connect('plateau.db')
   conn.row_factory = dict_factory
   cur = conn.cursor()
   all_ligne = cur.execute('SELECT * FROM ligne WHERE tableau_id=' + tableau_id + ' ORDER BY\
   ordre;').fetchall()

   tableau = []
   for ligne in all_ligne:
      tableau.append((ligne["Contenu"])) 

   final_tableau = []
   for i in range(0, len(tableau), 1):
      final_tableau.append({"ligne"+ str(i+1): str(tableau[i])})

   return jsonify(final_tableau)

app.run()