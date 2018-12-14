<?php 
    $idMedico = $_GET['idMedico'];
    $idDia = $_GET['idSemana'];

    $sql = "SELECT m.idMedico, m.nome, ha.horario_inicio, ha.horario_fim, s.diaSemana FROM medico AS M
INNER JOIN horario_atendimento AS ha ON ha.idMedico = m.idMedico
INNER JOIN dia_semana AS s ON ha.idDiaSemana = s.idDiaSemana
WHERE s.idDiaSemana = ".$idDia." AND m.idMedico =".$idMedico;

    $conexao = new mysqli("localhost", "root", "bcd127", "db_consulta_medicas");
    mysqli_set_charset($conexao, 'utf8');

    $select = mysqli_query($conexao, $sql);
    
    $lista = array();

    while($especialidade = mysqli_fetch_assoc($select)){
        $lista[] = $especialidade;
        
    }

    echo json_encode($lista);
?>