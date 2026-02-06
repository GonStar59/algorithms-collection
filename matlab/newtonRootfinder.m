function s=newtonRootfinder(f,x0,TOL)
% f puntero a la función (devuelve f(x) y f'(x)), x0  punto inicial
    for iter=1:10 
          [f0,fp0]=f(x0); % Llamo a f para obtener valores de f(x0) y f'(x0)   
          if f0==0, break; end  % He tenido suerte (me salgo)    
          x1 = x0-(f0/fp0);   % Iteracion de Newton.    
          fprintf('%2d -> %19.16f\n',iter,x1);  % Vuelco ultima iteracion   
          if abs(x0-x1)<TOL, break; end
          x0=x1;   % Actualizo x0 con el ultimo valor x1 para volver a iterar. 
    end 
    s = x1;  % Al final devuelvo el último término calculado. 
end