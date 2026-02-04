
function x=resuelve(A,b,p)
    [U, c] = gauss(A, b,p);
    x=solve_U(U,c);
end

function [U,c]=gauss(A,b,p)  % Eliminación de Gauss
N = length(b); % Tamaño datos
A = [A b];  % Pego vector b a la matriz A para hacerle lo mismo.
for col=1:N-1         % recorro columnas de 1 a N-1 de la matriz A  
  if p==1  %% Si p==1, ejecutar el código de pivotaje   
      v=A(col:N,col);
      [M,idx]=max(abs(v));
      Aux=A(col+idx-1,:);
      A(col+idx-1,:) = A(col,:);
      A(col,:) = Aux;
      auxV=b(col+idx-1);
      b(col+idx-1) = b(col);
      b(col) = auxV;
  end
  piv = A(col,col);    % Pivote = elemento de la diagonal
  for k=col+1:N       % Recorro filas (k's) por debajo de diagonal
    m = A(k,col)/piv;  % Cociente entre 1er elemento fila y pivote  
    A(k,col)=0;        % 1er elemento de la fila se hace 0 
    for j=col+1:size(A,2)  % Fila(k) = Fila(k) - m x fila(col)   
      A(k,j)=A(k,j)-m*A(col,j); % (desde columna col -> N)
    end   
  end      
end
U = A(:,1:N);     % Extraigo de A la matriz U triangular superior final
c = A(:,N+1:end); % Extraigo de A vector c del sistema Ux=c equivalente
end

function x=solve_U(U,b)%resolver una matriz triangular superior
    N=length(b);  x = NaN*zeros(N,1);
    for r=N:-1:1
        S=b(r);
        for k=r+1:N, S = S - U(r,k)*x(k); end
        x(r)=S/U(r,r);
    end
        
end

