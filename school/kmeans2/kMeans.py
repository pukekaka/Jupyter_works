from __future__ import division
import math, random
import os
from funct import squared_distance, vector_mean, distance
from functools import reduce


class KMeans:

    def __init__(self, k):
        self.k = k
        self.means = None

    def classify(self, input):
        #print(min(range(self.k), key=lambda i: squared_distance(input, self.means[i])))
        return min(range(self.k), key=lambda i: squared_distance(input, self.means[i]))

    def train(self, inputs):

        self.means = random.sample(inputs, self.k)
        assignments = None

        while True:
            # Find new assignments
            new_assignments = map(self.classify, inputs)
            #print(self.classify)

            print('assignments: ', assignments)
            print('new_assignments: ', new_assignments)

            # If no assignments have changed, we're done.
            if assignments == new_assignments:
                return

            # Otherwise keep the new assignments,
            assignments = new_assignments

            for i in range(self.k):
                i_points = [p for p, a in zip(inputs, assignments) if a == i]
                # avoid divide-by-zero if i_points is empty
                if i_points:
                    self.means[i] = vector_mean(i_points)


if __name__ == "__main__":

    inputs = [[-14,-5],[13,13],[20,23],[-19,-11],[-9,-16],[21,27],[-49,15],[26,13],[-46,5],[-34,-1],[11,15],[-49,0],[-22,-16],[19,28],[-12,-8],[-13,-19],[-41,8],[-11,-6],[-25,-9],[-18,-3]]

    random.seed(0) # so you get the same results as me
    clusterer = KMeans(3)
    clusterer.train(inputs)
    print ("3-means:", clusterer.means)

'''
    print "errors as a function of k"

    for k in range(1, len(inputs) + 1):
        print k, squared_clustering_errors(inputs, k)
    print
'''