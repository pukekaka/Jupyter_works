from __future__ import print_function

import os
import subprocess

import pandas as pd
import numpy as np
from sklearn.tree import DecisionTreeClassifier, export_graphviz
from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_val_score


def visualize_tree(tree, feature_names):
    """Create tree png using graphviz.

    Args
    ----
    tree -- scikit-learn DecsisionTree.
    feature_names -- list of feature names.
    """
    with open("dt_cv.dot", 'w') as f:
        export_graphviz(tree, out_file=f, feature_names=feature_names)

    command = ["dot", "-Tpng", "dt_cv.dot", "-o", "dt_cv.png"]
    try:
        subprocess.check_call(command)
    except:
        exit("Could not run dot, ie graphviz, to produce visualization")


def encode_target(df, target_column):
    """Add column to df with integers for the target.
    Args
    ----
    df -- pandas DataFrame.
    target_column -- column to map to int, producing new Target column.
    Returns
    -------
    df -- modified DataFrame.
    targets -- list of target names.
    """
    df_mod = df.copy()
    targets = df_mod[target_column].unique()
    map_to_int = {name: n for n, name in enumerate(targets)}
    df_mod["Target"] = df_mod[target_column].replace(map_to_int)

    return (df_mod, targets)

def get_iris_data():
    """Get the iris data, from local csv or pandas repo."""
    if os.path.exists("iris.csv"):
        print("-- iris.csv found locally")
        df = pd.read_csv("iris.csv", index_col=0)
    else:
        print("-- trying to download from github")
        fn = "https://raw.githubusercontent.com/pydata/pandas/" + \
             "master/pandas/tests/data/iris.csv"
        try:
            df = pd.read_csv(fn)
        except:
            exit("-- Unable to download iris.csv")

        with open("iris.csv", 'w') as f:
            print("-- writing to local iris.csv file")
            df.to_csv(f)

    return df

if __name__ == '__main__':
    print("\n-- get data:")
    df = get_iris_data()

    print("\n-- df.shape:")
    print(df.shape, end="\n")
    print("\n-- df.head():")
    print(df.head(), end="\n\n")

    features = ["SepalLength", "SepalWidth", "PetalLength", "PetalWidth"]
    df, targets = encode_target(df, "Name")
    y = df["Target"]
    X = df[features]
    X_train, X_test, y_train, y_test = train_test_split(df[features], df["Target"], random_state=0)

    dt = DecisionTreeClassifier(min_samples_split=20, random_state=99)
    dt.fit(X_train, y_train)

    scores3 = cross_val_score(dt, df[features], df["Target"])
    print("cross_val_score(3-fold): {}".format(scores3))

    scores5 = cross_val_score(dt, df[features], df["Target"], cv=5)
    print("cross_val_score(5-fold): {}".format(scores5))

    scores5_mean = cross_val_score(dt, df[features], df["Target"], cv=5)
    print("cross_val_score(5-fold_means): {:.2f}".format(scores5_mean.mean()))

    scores20_mean = cross_val_score(dt, df[features], df["Target"], cv=20)
    print("cross_val_score(20-fold-means: {}".format(scores20_mean.mean()))

    scores10_mean = cross_val_score(dt, df[features], df["Target"], cv=10)
    print("cross_val_score(10-fold_means): {}".format(scores10_mean.mean()))

    #visualize_tree(dt, features)