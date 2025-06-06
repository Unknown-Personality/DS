#include <mpi.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
    int rank, size, n = 8;
    int data[8] = {10, 20, 30, 40, 50, 60, 70, 80};  // Example array
    int local_sum = 0, global_sum = 0;

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);  // Get current process rank
    MPI_Comm_size(MPI_COMM_WORLD, &size);  // Get total number of processes

    int elements_per_proc = n / size;
    int* sub_data = (int*)malloc(elements_per_proc * sizeof(int));

    // Scatter the data to all processes
    MPI_Scatter(data, elements_per_proc, MPI_INT, sub_data, elements_per_proc, MPI_INT, 0, MPI_COMM_WORLD);

    // Each process computes its local sum
    for (int i = 0; i < elements_per_proc; i++) {
        local_sum += sub_data[i];
    }

    printf("Process %d: Local sum = %d\n", rank, local_sum);

    // Reduce all local sums into global sum at root (rank 0)
    MPI_Reduce(&local_sum, &global_sum, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    if (rank == 0) {
        printf("Global Sum = %d\n", global_sum);
    }

    free(sub_data);
    MPI_Finalize();
    return 0;
}


// mpicc --version
// mpirun --version
// sudo apt update
// sudo apt install mpich
// mpicc sum_mpi.c -o sum_mpi
// mpirun -np 4 ./sum_mpi