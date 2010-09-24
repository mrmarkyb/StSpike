
$document(
    head={
      ${example}:title()$
      $includes()$ 
    },
    content={
      $list(["Hello","World"])$
     

      $table({
        $row(["name", "time", "id"])$
        $transactions:{transaction| $row([{$transaction.name$},{$transaction.time$},{$transaction.id$}])$}$
        })$
    }
)$